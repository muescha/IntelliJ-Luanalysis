/*
 * Copyright (c) 2017. tangzx(love.tangzx@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tang.intellij.lua.ty

import com.intellij.psi.stubs.StubInputStream
import com.intellij.psi.stubs.StubOutputStream
import com.tang.intellij.lua.search.SearchContext

class TyMultipleResults(val list: List<ITy>, val variadic: Boolean) : Ty(TyKind.MultipleResults) {

    override fun substitute(substitutor: ITySubstitutor): ITy {
        var resultsSubstituted = false
        val substitutedResults = list.map {
            val substitutedResult = it.substitute(substitutor)

            if (substitutedResult !== it) {
                resultsSubstituted = true
            }

            substitutedResult
        }
        return if (resultsSubstituted) {
            TyMultipleResults(substitutedResults, variadic)
        } else {
            this
        }
    }

    override fun accept(visitor: ITyVisitor) {
        visitor.visitMultipleResults(this)
    }

    override fun acceptChildren(visitor: ITyVisitor) {
        list.forEach { it.accept(visitor) }
    }

    override fun contravariantOf(other: ITy, context: SearchContext, flags: Int): Boolean {
        val requiredSize = if (variadic) list.size - 1 else list.size
        val flattenedOther = TyMultipleResults.flatten(other)

        if (flattenedOther is TyMultipleResults) {
            if (flattenedOther.variadic) {
                if (!variadic) {
                    return false
                }
            } else {
                if (flattenedOther.list.size < requiredSize) {
                    return false
                }
            }

            for (i in 0 until flattenedOther.list.size) {
                val otherTy = flattenedOther.list[i]
                val thisTy = if (i >= list.size) {
                    if (variadic) list.last() else return true
                } else list[i]

                if (!thisTy.covariantOf(otherTy, context, flags)) {
                    return false
                }
            }

            return true
        }

        return requiredSize <= 1 && list.first().contravariantOf(other, context, flags)
    }

    override fun hashCode(): Int {
        var hash = if (variadic) 1 else 0
        for (ty in list) {
            hash = hash * 31 + ty.hashCode()
        }
        return hash
    }

    override fun equals(other: Any?): Boolean {
        if (other is TyMultipleResults && other.variadic == variadic && other.list.size == list.size) {
            for (i in 0 until list.size) {
                if (list[i] != other.list[i]) {
                    return false
                }
            }
            return true
        }
        return super.equals(other)
    }

    override fun equals(other: ITy, context: SearchContext): Boolean {
        if (this === other) {
            return true
        }

        val resolvedOther = Ty.resolve(other, context)

        return resolvedOther is TyMultipleResults
                && resolvedOther.variadic == variadic
                && resolvedOther.list.size == list.size
                && resolvedOther.list.asSequence().zip(list.asSequence()).all { (otherTy, ty) -> otherTy.equals(ty, context) }
    }

    companion object {
        fun getResult(ty: ITy, index: Int = 0): ITy {
            val flattenedTy = TyMultipleResults.flatten(ty)

            return if (flattenedTy is TyMultipleResults) {
                if (index < flattenedTy.list.lastIndex) {
                    getResult(flattenedTy.list.get(index))
                } else if (flattenedTy.variadic) {
                    val lastResult = flattenedTy.list.last()
                    val variadicTy = getResult(lastResult).union(getResult(lastResult, index - flattenedTy.list.lastIndex))
                    Ty.NIL.union(variadicTy)
                } else {
                    getResult(flattenedTy.list.last(), index - flattenedTy.list.lastIndex)
                }
            } else if (index == 0) {
                flattenedTy
            } else {
                Ty.NIL
            }
        }

        fun flatten(ty: ITy): ITy {
            if (ty !is TyUnion) {
                return ty
            }

            val tyList = mutableListOf<ITy>()
            var variadicTy: ITy? = null

            TyUnion.each(ty) {
                if (it is TyMultipleResults) {
                    val multipleResults = it

                    multipleResults.list.forEachIndexed { index, resultTy ->
                        if (index < tyList.size) {
                            tyList[index] = tyList[index].union(resultTy)
                        } else if (!multipleResults.variadic || index < multipleResults.list.lastIndex) {
                            tyList.add(variadicTy?.union(resultTy) ?: resultTy)
                        }
                    }

                    if (multipleResults.variadic) {
                        val multipleResultsVariadicTy = multipleResults.list.last()

                        for (i in multipleResults.list.size until tyList.size) {
                            tyList[i] = tyList[i].union(multipleResultsVariadicTy)
                        }

                        variadicTy = variadicTy?.union(multipleResultsVariadicTy) ?: multipleResultsVariadicTy
                    }
                } else {
                    if (tyList.isEmpty()) {
                        tyList.add(it)
                    } else {
                        tyList[0] = tyList[0].union(it)
                    }
                }
            }

            if (tyList.size == 1 && variadicTy == null) {
                return tyList[0]
            }

            variadicTy?.let { tyList.add(it) }
            return TyMultipleResults(tyList.toList(), variadicTy != null)
        }
    }
}

object TyMultipleResultsSerializer : TySerializer<TyMultipleResults>() {
    override fun deserializeTy(flags: Int, stream: StubInputStream): TyMultipleResults {
        val size = stream.readByte().toInt()
        val list = mutableListOf<ITy>()
        for (i in 0 until size) list.add(Ty.deserialize(stream))
        val variadic = stream.readBoolean()
        return TyMultipleResults(list, variadic)
    }

    override fun serializeTy(ty: TyMultipleResults, stream: StubOutputStream) {
        stream.writeByte(ty.list.size)
        ty.list.forEach { Ty.serialize(it, stream) }
        stream.writeBoolean(ty.variadic)
    }
}