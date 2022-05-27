package sometime.interpreter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import sometime.ast.AST;
import sometime.exception.NodeVisitorException;

public class NodeVisitor {
    Object visit(AST node) throws NoSuchMethodException, NodeVisitorException {
        String methodName = "visit_" + node.getClass().getSimpleName();
        //System.out.println(methodName);
        try {
            Method method = this.getClass().getDeclaredMethod(
                    methodName, new Class[]{ AST.class }
            );
            return method.invoke(this, node);
        } catch (NoSuchMethodException ex) {
            return genericVisit(node);
        } catch (IllegalAccessException | IllegalArgumentException
                | SecurityException | InvocationTargetException ex) {
            throw new NodeVisitorException(ex);
        }
    }

    Object genericVisit(AST node) throws NoSuchMethodException {
        throw new NoSuchMethodException("No visit method for node: "
                + node.getClass().getSimpleName()
        );
    }
}
