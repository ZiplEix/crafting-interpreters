/*
** PERSONAL PROJECT, 2024
** crafting-interpreters [WSL : Ubuntu]
** File description:
** value
*/

#pragma once

#include "clox.h"

typedef double Value;

typedef struct {
    int capacity;
    int count;
    Value *values;
} ValueArray;

void initValueArray(ValueArray* array);
void writeValueArray(ValueArray* array, Value value);
void freeValueArray(ValueArray* array);
void printValue(Value value);
