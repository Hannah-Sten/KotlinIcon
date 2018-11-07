package nl.rubensten.kotlinicon

import com.intellij.psi.PsiFile

val PsiFile.hasTopLevelCallables: Boolean
    get() {
        val hasTopLevelCallablesField = javaClass.getDeclaredField("hasTopLevelCallables")
        hasTopLevelCallablesField.isAccessible = true
        return hasTopLevelCallablesField.get(this) == true
    }