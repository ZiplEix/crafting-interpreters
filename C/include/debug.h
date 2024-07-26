/*
** EPITECH PROJECT, 2024
** crafting-interpreters [WSL : Ubuntu]
** File description:
** debug
*/

#pragma once

#include "chunk.h"

void disassembleChunk(Chunk *chunk, const char *name);
int disassembleInstruction(Chunk *chunk, int offset);
