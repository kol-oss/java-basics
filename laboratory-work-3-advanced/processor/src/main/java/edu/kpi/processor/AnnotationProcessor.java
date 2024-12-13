package edu.kpi.processor;

import edu.kpi.annotations.Table;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@javax.annotation.processing.SupportedAnnotationTypes({"edu.kpi.annotations.Table", "edu.kpi.annotations.Column"})
@javax.annotation.processing.SupportedSourceVersion(SourceVersion.RELEASE_16)
public class AnnotationProcessor extends AbstractProcessor {
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            if (!annotation.getQualifiedName().toString().equals(Table.class.getCanonicalName())) continue;

            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                if (!element.getKind().isClass()) continue;

                this.generateRepositoryClass(element);
            }
        }

        return true;
    }

    private void generateRepositoryClass(Element element) {
        String className = element.getSimpleName() + "SQLRepository";
        String packageName = this.processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString();
        String entityName = element.getSimpleName().toString();

        try {
            JavaFileObject builderFile = this.filer.createSourceFile(packageName + "." + className);
            try (Writer writer = builderFile.openWriter()) {
                writer.write("package " + packageName + ";\n\n");
                writer.write("import edu.kpi.reflection.builder.Entity;\n");
                writer.write("import edu.kpi.reflection.builder.EntityBuilder;\n");
                writer.write("import edu.kpi.reflection.generator.reflection.ReflectionGenerator;\n\n");

                writer.write("public class " + className + " {\n");
                writer.write("    private final Entity entity;\n\n");

                writer.write("    public " + className + "(" + entityName + " entityInstance) {\n");
                writer.write("        this.entity = new EntityBuilder(new ReflectionGenerator(entityInstance)).build();\n");
                writer.write("    }\n\n");

                writer.write("    public String insert() {\n");
                writer.write("        return this.entity.insert();\n");
                writer.write("    }\n\n");

                writer.write("    public String select() {\n");
                writer.write("        return this.entity.select();\n");
                writer.write("    }\n\n");

                writer.write("    public String update() {\n");
                writer.write("        return this.entity.update();\n");
                writer.write("    }\n\n");

                writer.write("    public String delete() {\n");
                writer.write("        return this.entity.delete();\n");
                writer.write("    }\n");

                writer.write("}\n");
            }
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Failed to generate repository class: " + e.getMessage());
        }
    }
}