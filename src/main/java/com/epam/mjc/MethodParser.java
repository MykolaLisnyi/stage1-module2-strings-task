package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier = null;
        String returnType;
        String methodName;
        List<MethodSignature.Argument> arguments = new ArrayList<>();

        int openParenthesesIndex = signatureString.indexOf("(");
        int closeParenthesesIndex = signatureString.indexOf(")");
        String splitArgRegex = "\\s*,\\s+";
        String argumentsString = signatureString.substring(openParenthesesIndex + 1, closeParenthesesIndex);
        for (String typeAndName : argumentsString.split(splitArgRegex)) {
            if (!typeAndName.isEmpty()) {
                String argType = typeAndName.split("\\s")[0];
                String argName = typeAndName.split("\\s")[1];
                arguments.add(new MethodSignature.Argument(argType, argName));
            }
        }

        StringTokenizer stringTokenizer = new StringTokenizer(signatureString.substring(0, openParenthesesIndex));
        if (stringTokenizer.countTokens() == 3) {
            accessModifier = stringTokenizer.nextToken();
        }
        returnType = stringTokenizer.nextToken();
        methodName = stringTokenizer.nextToken();

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setReturnType(returnType);
        methodSignature.setAccessModifier(accessModifier);

        return methodSignature;
    }
}