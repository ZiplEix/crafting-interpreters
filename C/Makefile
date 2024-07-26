##
## PERSONAL PROJECT, 2023
## myteams [WSL : Debian]
## File description:
## Makefile
##

#### PROJECT SETTINGS ####
# The name of the executable to be created
BIN_NAME := clox
# Compiler used
CC ?= gcc
# Extension of source files used in the project
SRC_EXT = c
# Path to the source directory, relative to the makefile
SRC_PATH = .
# Space-separated pkg-config libraries used by this project
LIBS =
# General compiler flags
COMPILE_FLAGS = -g -g3
# Add additional include paths
INCLUDES = -I $(SRC_PATH)/include
#### END PROJECT SETTINGS ####

###############################
# DO NOT EDIT BELOW THIS LINE #
###############################

# Obtains the OS type, either 'Darwin' (OS X) or 'Linux'
UNAME_S:=$(shell uname -s)

# Shell used in this makefile
# bash is used for 'echo -en'
SHELL = /bin/bash

# Append pkg-config specific libraries if need be
# ifneq ($(LIBS),)
# 	COMPILE_FLAGS += $(shell pkg-config --cflags $(LIBS))
# 	LINK_FLAGS += $(shell pkg-config --libs $(LIBS))
# endif

# Verbose option, to output compile and link commands
export V := false
export CMD_PREFIX := @
ifeq ($(V),true)
	CMD_PREFIX :=
endif

# Combine compiler and linker flags
all: export CFLAGS := $(CFLAGS) $(COMPILE_FLAGS) $(LIBS)
all: export LDFLAGS := $(LDFLAGS) $(LINK_FLAGS) $(LIBS)

# Build and output paths
all: export BUILD_PATH := ./build
all: export BIN_PATH := ./bin

# Find all source files in the source directory, sorted by most
# recently modified
ifeq ($(UNAME_S),Darwin)
	SOURCES = $(shell find $(SRC_PATH) -name '*.$(SRC_EXT)' \
						| sort -k 1nr | cut -f2-)
else
	SOURCES = $(shell find $(SRC_PATH) -name '*.$(SRC_EXT)' \
						-printf '%T@\t%p\n' | sort -k 1nr | cut -f2-)
endif

# fallback in case the above fails
rwildcard = $(foreach d, $(wildcard $1*), $(call rwildcard,$d/,$2) \
						$(filter $(subst *,%,$2), $d))
ifeq ($(SOURCES),)
	SOURCES := $(call rwildcard, $(SRC_PATH), *.$(SRC_EXT))
endif

# Set the object file names, with the source directory stripped
# from the path, and the build path prepended in its place
OBJECTS = $(SOURCES:$(SRC_PATH)/%.$(SRC_EXT)=$(BUILD_PATH)/%.o)
# Set the dependency files that will be used to add header dependencies
DEPS = $(OBJECTS:.o=.d)

# Macros for timing compilation
ifeq ($(UNAME_S),Darwin)
	CUR_TIME = awk 'BEGIN{srand(); print srand()}'
	TIME_FILE = $(dir $@).$(notdir $@)_time
	START_TIME = $(CUR_TIME) > $(TIME_FILE)
	END_TIME = read st < $(TIME_FILE) ; \
		$(RM) $(TIME_FILE) ; \
		st=$$((`$(CUR_TIME)` - $$st)) ; \
		echo $$st
else
	TIME_FILE = $(dir $@).$(notdir $@)_time
	START_TIME = date '+%s' > $(TIME_FILE)
	END_TIME = read st < $(TIME_FILE) ; \
		$(RM) $(TIME_FILE) ; \
		st=$$((`date '+%s'` - $$st - 86400)) ; \
		echo `date -u -d @$$st '+%H:%M:%S'`
endif

.PHONY: all
all: dirs
	@echo "Beginning build"
	@$(START_TIME)
	@$(MAKE) compilation --no-print-directory
	@echo -n "Total build time: "
	@$(END_TIME)

# Main rule, checks the executable and symlinks to the output
.PHONY: compilation
compilation: $(BIN_PATH)/$(BIN_NAME)
	@echo "Making symlink: $(BIN_NAME) -> $<"
	@$(RM) $(BIN_NAME)
	@ln -s $(BIN_PATH)/$(BIN_NAME) $(BIN_NAME)

# Create the directories used in the build
.PHONY: dirs
dirs:
	@echo "Creating directories"
	@mkdir -p $(dir $(OBJECTS))
	@mkdir -p $(BIN_PATH)

.PHONY: clean
clean:
	@echo "Deleting directories"
	@$(RM) -rv build
	@$(RM) -rv null.d
	@$(RM) -rv .all_time

# Removes all build files
.PHONY: fclean
fclean: clean
	@$(RM) -rv bin
	@echo "Deleting $(BIN_NAME) symlink"
	@$(RM) -v $(BIN_NAME)

re: fclean
	@echo "Re-building $(BIN_NAME)"
	@make

.PHONY:	testor
testor:	all
	cp ./panoramix testor/ && cd testor/ && python3 mouli.py

# Link the executable
$(BIN_PATH)/$(BIN_NAME): $(OBJECTS)
	@echo "Linking: $@"
	$(CMD_PREFIX) $(CC) $(OBJECTS) $(LDFLAGS) -o $@

# Add dependency files, if they exist
-include $(DEPS)

# Source file rules
# After the first compilation they will be joined with the rules from the
# dependency files to provide header dependencies
$(BUILD_PATH)/%.o: $(SRC_PATH)/%.$(SRC_EXT)
	@echo -e "\e[35mCompiling: $< -> $@\e[0m"
	$(CMD_PREFIX) $(CC) $(CFLAGS) $(INCLUDES) -MP -MMD -c $< -o $@
