package itschool.xcalculator.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Renderer {

    public String render(Node node) {
        return renderInternal(node, null);
    }

    private String renderInternal(@NonNull Node node, @Nullable Node parentNode) {
        if (node.isPolynomial()) {
            return node.getPolynomial().toString();
        } else if (node.isFunction()) {
            String functionName = node.getToken().content;
            String functionArg = renderInternal(node.getLeft(), null);
            return String.format("%s(%s)", functionName, functionArg);
        } else if (node.isOperator()) {
            String operatorName = node.getToken().content;
            String leftOperand = renderInternal(node.getLeft(), node);
            String rightOperand = renderInternal(node.getRight(), node);
            boolean needAddBrackets = parentNode != null &&
                    parentNode.getToken().getPriority() <= node.getToken().getPriority();
            String result = String.format("%s%s%s", leftOperand, operatorName, rightOperand);
            if (needAddBrackets) {
                return String.format("(%s)", result);
            } else {
                return result;
            }
        } else {
            throw new RuntimeException("Incompatible node type for rendering!");
        }
    }

}

















