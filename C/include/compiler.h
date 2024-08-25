/*
** PERSONAL PROJECT, 2024
** crafting-interpreters [WSL : Ubuntu]
** File description:
** compiler
*/

#pragma once

#include "object.h"
#include "vm.h"
#include "chunk.h"

ObjFunction* compile(const char* source);
