// This is a generated file. Not intended for manual editing.
package com.tang.intellij.lua.comment.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.tang.intellij.lua.psi.LuaTypeField;
import com.tang.intellij.lua.psi.LuaTypeAlias;

public class LuaDocVisitor extends PsiElementVisitor {

  public void visitAccessModifier(@NotNull LuaDocAccessModifier o) {
    visitPsiElement(o);
  }

  public void visitArrTy(@NotNull LuaDocArrTy o) {
    visitTy(o);
    // visitLuaClassMember(o);
  }

  public void visitBooleanLiteralTy(@NotNull LuaDocBooleanLiteralTy o) {
    visitTy(o);
  }

  public void visitCommentString(@NotNull LuaDocCommentString o) {
    visitPsiElement(o);
  }

  public void visitFunctionParam(@NotNull LuaDocFunctionParam o) {
    visitPsiElement(o);
  }

  public void visitFunctionParams(@NotNull LuaDocFunctionParams o) {
    visitPsiElement(o);
  }

  public void visitFunctionReturnType(@NotNull LuaDocFunctionReturnType o) {
    visitPsiElement(o);
  }

  public void visitFunctionTy(@NotNull LuaDocFunctionTy o) {
    visitTy(o);
    // visitLuaTypeScope(o);
  }

  public void visitGeneralTy(@NotNull LuaDocGeneralTy o) {
    visitTy(o);
  }

  public void visitGenericDef(@NotNull LuaDocGenericDef o) {
    visitPsiNameIdentifierOwner(o);
    // visitLuaClass(o);
    // visitLuaScopedType(o);
  }

  public void visitGenericTableIndexTy(@NotNull LuaDocGenericTableIndexTy o) {
    visitTy(o);
  }

  public void visitGenericTableTy(@NotNull LuaDocGenericTableTy o) {
    visitTy(o);
    // visitLuaClassMember(o);
  }

  public void visitGenericTy(@NotNull LuaDocGenericTy o) {
    visitTy(o);
  }

  public void visitNumberLiteralTy(@NotNull LuaDocNumberLiteralTy o) {
    visitTy(o);
  }

  public void visitParTy(@NotNull LuaDocParTy o) {
    visitTy(o);
  }

  public void visitParamNameRef(@NotNull LuaDocParamNameRef o) {
    visitPsiElement(o);
  }

  public void visitPrimitiveTableTy(@NotNull LuaDocPrimitiveTableTy o) {
    visitTy(o);
    // visitLuaClassMember(o);
  }

  public void visitReturnList(@NotNull LuaDocReturnList o) {
    visitType(o);
  }

  public void visitSnippetTy(@NotNull LuaDocSnippetTy o) {
    visitTy(o);
  }

  public void visitStringLiteralTy(@NotNull LuaDocStringLiteralTy o) {
    visitTy(o);
  }

  public void visitTableField(@NotNull LuaDocTableField o) {
    visitPsiElement(o);
    // visitLuaClassField(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitTableDef(@NotNull LuaDocTableDef o) {
    visitPsiElement(o);
  }

  public void visitTableTy(@NotNull LuaDocTableTy o) {
    visitTy(o);
  }

  public void visitTagAlias(@NotNull LuaDocTagAlias o) {
    visitLuaTypeAlias(o);
    // visitPsiNameIdentifierOwner(o);
    // visitTag(o);
  }

  public void visitTagClass(@NotNull LuaDocTagClass o) {
    visitPsiElement(o);
    // visitPsiNameIdentifierOwner(o);
    // visitLuaClass(o);
    // visitLuaScopedType(o);
    // visitLuaTypeScope(o);
    // visitTag(o);
  }

  public void visitTagDef(@NotNull LuaDocTagDef o) {
    visitTag(o);
  }

  public void visitTagField(@NotNull LuaDocTagField o) {
    visitLuaClassField(o);
    // visitPsiNameIdentifierOwner(o);
    // visitTag(o);
  }

  public void visitTagGenericList(@NotNull LuaDocTagGenericList o) {
    visitTag(o);
  }

  public void visitTagLan(@NotNull LuaDocTagLan o) {
    visitTag(o);
  }

  public void visitTagNot(@NotNull LuaDocTagNot o) {
    visitTag(o);
  }

  public void visitTagOverload(@NotNull LuaDocTagOverload o) {
    visitTag(o);
  }

  public void visitTagParam(@NotNull LuaDocTagParam o) {
    visitTag(o);
  }

  public void visitTagReturn(@NotNull LuaDocTagReturn o) {
    visitTag(o);
  }

  public void visitTagSee(@NotNull LuaDocTagSee o) {
    visitTag(o);
  }

  public void visitTagSuppress(@NotNull LuaDocTagSuppress o) {
    visitTag(o);
  }

  public void visitTagType(@NotNull LuaDocTagType o) {
    visitTag(o);
  }

  public void visitTagVararg(@NotNull LuaDocTagVararg o) {
    visitTag(o);
  }

  public void visitTy(@NotNull LuaDocTy o) {
    visitType(o);
  }

  public void visitTypeList(@NotNull LuaDocTypeList o) {
    visitPsiElement(o);
  }

  public void visitTypeRef(@NotNull LuaDocTypeRef o) {
    visitPsiElement(o);
  }

  public void visitUnionTy(@NotNull LuaDocUnionTy o) {
    visitTy(o);
  }

  public void visitVarargParam(@NotNull LuaDocVarargParam o) {
    visitPsiElement(o);
  }

  public void visitPsiNameIdentifierOwner(@NotNull PsiNameIdentifierOwner o) {
    visitElement(o);
  }

  public void visitTag(@NotNull LuaDocTag o) {
    visitPsiElement(o);
  }

  public void visitType(@NotNull LuaDocType o) {
    visitPsiElement(o);
  }

  public void visitLuaClassField(@NotNull LuaTypeField o) {
    visitElement(o);
  }

  public void visitLuaTypeAlias(@NotNull LuaTypeAlias o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull LuaDocPsiElement o) {
    visitElement(o);
  }

}
