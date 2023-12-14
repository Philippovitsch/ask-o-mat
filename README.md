# ASK-O-MAT

## Description

This is a command line tool that gives you two options:
- Ask specific questions
- Add questions and their answers

## Rules

- A question/ answer must not contain more than 255 characters
- A question can have multiple answers and must adhere to the following format:
  - `<question>? “<answer1>” “<answer2>” “<answerX>”`

## Run locally

Tested in a Linux (Debian) environment:

- Open a Terminal and clone the project:
```ssh
git clone git@github.com:Philippovitsch/ask-o-mat.git
```

- Navigate to the project folder and compile
```ssh
cd ask-o-mat/ && mvn package
```

- Navigate to the compiled package and start it
```ssh
cd target/ && java -jar ask-o-mat-1.0.jar
```
