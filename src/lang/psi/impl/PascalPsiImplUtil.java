package com.siberika.idea.pascal.lang.psi.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.util.PsiTreeUtil;
import com.siberika.idea.pascal.lang.parser.PascalFile;
import com.siberika.idea.pascal.lang.psi.PasModule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Author: George Bakhtadze
 * Date: 12/25/12
 */
public class PascalPsiImplUtil {
    @NotNull
    public static PsiReference[] getReferences(PasModule o) {
        final PascalFile file = PsiTreeUtil.getParentOfType(o, PascalFile.class);
        if (file == null) return PsiReference.EMPTY_ARRAY;
        return new PsiReference[] {
                new PsiReferenceBase<PascalFile>(file, TextRange.from(o.getStartOffsetInParent(), o.getTextLength())) {
                    @Override
                    public PsiElement resolve() {
                        return file.getContainingFile();
                    }

                    @NotNull
                    @Override
                    public Object[] getVariants() {
                        final ArrayList<LookupElement> list = new ArrayList<LookupElement>();

                        return list.toArray(new Object[list.size()]);
                    }
                }
        };
    }
}
