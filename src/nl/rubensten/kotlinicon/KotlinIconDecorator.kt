package nl.rubensten.kotlinicon

import com.intellij.ide.FileIconProvider
import com.intellij.ide.IconProvider
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.projectView.ProjectViewNode
import com.intellij.ide.projectView.ProjectViewNodeDecorator
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.packageDependencies.ui.PackageDependenciesNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.ui.ColoredTreeCellRenderer
import javax.swing.Icon

/**
 * @author Ruben Schellekens
 */
class KotlinIconDecorator : IconProvider(), ProjectViewNodeDecorator, FileIconProvider {

    companion object {
        val KOTLIN_ICON = IconLoader.getIcon("/icon.png")
    }

    override fun getIcon(element: PsiElement, flags: Int): Icon? {
        if (element is PsiFile && element.name.endsWith(".kt") && element.hasTopLevelCallables) {
            return KOTLIN_ICON
        }

        return null
    }

    override fun decorate(node: ProjectViewNode<*>?, presentation: PresentationData?) {
        val file = node?.virtualFile ?: return
        if (file.isDirectory) {
            return
        }

        val extension: String = file.extension ?: return
        when (extension) {
            "kt" -> {
                presentation?.setIcon(KOTLIN_ICON)
                presentation?.presentableText = file.nameWithoutExtension
            }
        }
    }

    override fun decorate(node: PackageDependenciesNode?, cellRenderer: ColoredTreeCellRenderer?) {
        // Do nothing.
    }

    override fun getIcon(file: VirtualFile, flags: Int, project: Project?): Icon? {
        if (file.extension == "kt") {
            return KOTLIN_ICON
        }

        return null
    }
}