# Java Development Environment with Docker

This projects sets up a Java development environment using Docker. It allows you to compile and run Java programs without having to install Java directly on your machine.

## Prerequisites

- Docker installed on your machine. You can download it from [Docker's official website](https://www.docker.com/get-started).

## Setup

1. Clone this repository or download the files to your local machine.
2. Ensure you have the following files in your project directory:
   - `Dockerfile`
   - `run_docker.sh`
   - `HelloWorld.java` (or any other Java source files you want to work with)

## How to use

### Build and Run the Docker Container

To build the Docker image and run the container, use the following command:

```bash
./run_docker.sh build
```

This command will:

1. Build the Docker image using the `Dockerfile`.
2. Run the Docker container with the current directory mounted, allowing you to access and modify files from your local machine inside the container.

### Run the Docker Container Only

If you have already built the Docker image and just want to run the container, use:

```bash
./run_docker.sh
```

### Compile and Run Java Programs

Once inside the Docker container, you can compile and run your Java programs. For example, to compile and run HelloWorld.java, use:

```bash
javac HelloWorld.java
java HelloWorld
```

You should see the following output:

```txt
Hello, World!
```

### Exit the Docker Container

To exit the Docker container, you can use the `exit` command or press `Ctrl + D`.

## Notes

- The `run_docker.sh` script mounts the current directory to `/usr/src/app` inside the Docker container. This means any changes you make to files in your local directory will be reflected inside the container and vice versa.

- The container runs with a `bash` shell by default, allowing you to use it interactively.

## Troubleshooting

- Ensure Docker is running on your machine.
- Make sure you have execution permissions for run_docker.sh by running `chmod +x run_docker.sh`.
- Creating a file with a command inside the dockerfile won't allow you to modifie the file from the host machine. You need to create the file from the host machine and then run the command inside the container.
