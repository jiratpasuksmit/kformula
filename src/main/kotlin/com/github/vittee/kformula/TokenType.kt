package com.github.vittee.kformula

internal enum class TokenType {
    NONE,
    NUMBER, VARIABLE, NAME,
    PLUS, MINUS, TIMES, DIVIDE, EXPONENT, DOT_DOT,

    B_LEFT, B_RIGHT, COMMA, EXCLAMATION, EX_EQ, GREATER, GREATER_EQ, EQUAL, EQUAL_EQUAL, LESS, LESS_EQ, NOT_EQ,

    AND, OR, NOT, MOD, TRUE, FALSE,
    IF, THEN, ELSE,
    IN
}