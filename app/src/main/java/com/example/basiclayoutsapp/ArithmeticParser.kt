package com.example.basiclayoutsapp

import kotlin.math.pow

fun parseToken(input : String) : ArrayList<String> {
    var tokens = ArrayList<String>()
    val stack = ArrayDeque<String>()
    var output = ArrayList<String>()

    var pos = 0

    while (pos < input.length){
        if (input[pos].isDigit()){
            var sb = StringBuilder()
            while (pos < input.length && (input[pos].isDigit() || input[pos] == '.')){
                sb.append(input[pos])
                pos++
            }
            tokens.add(sb.toString())
        }
        else if (input[pos].isWhitespace()) pos++
        else {
            tokens.add(input[pos].toString())
            pos++
        }
    }

    for (str in tokens){
        if (str[0].isDigit()) output.add(str)
        else if (str == "(") stack.addLast(str)
        else if (str == ")"){
            while (stack.last() != "("){
                output.add(stack.removeLast())
            }
            stack.removeLast()
        }
        else {
            while (stack.isNotEmpty() && stack.last() != "(" && precedence(stack.last()) >= precedence(str)){
                output.add(stack.removeLast())
            }
            stack.addLast(str)
        }
    }
    while (stack.isNotEmpty()) {
        output.add(stack.removeLast())
    }
    return output
}

fun precedence(str : String) : Int{
    if (str == "^") return 3
    if (str == "×" || str == "÷") return 2
    if (str == "+" || str == "-") return 1
    return 0
}

fun evaluate(tokens : ArrayList<String>) : Double{
    val stack = ArrayDeque<Double>()
    for (token in tokens){
        if (token[0].isDigit()){
            stack.addLast(token.toDouble())
        }
        else {
            val b = stack.removeLast()
            val a = stack.removeLast()
            when (token){
                "+" -> stack.addLast(a + b)
                "-" -> stack.addLast(a - b)
                "×" -> stack.addLast(a * b)
                "÷" -> stack.addLast(a / b)
                "^" -> stack.addLast(a.pow(b))
            }
        }
    }
    return stack.removeLast()
}