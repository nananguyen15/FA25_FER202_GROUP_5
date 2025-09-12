# Assignment Base Branch

## Introduction

This is the base branch of class assignments. You are expected to create a
separate branch for each assignment based on this branch. You MUST NOT create an
assignment branch from the main branch. To create a new assignment branch, run
this in your terminal:

```sh
git fetch origin && git checkout assignment/<your-assignment-name> origin/assignment/base
```

It is encouraged that you use the assignment name for easier identification.

## Create Vite

We will use [Vite](https://vite.dev/guide/) to create our React app.
Vite is more modern than Creat React App, and so has less problems.
To create a project with Vite, run this in your terminal:

```sh
npm create vite@latest
```

Then your can `cd` into the folder of the new project and install
the dependencies:

```sh
cd <your-project-name> && npm install
```

## Assignments

View the base branch [here](https://github.com/nananguyen15/FA25_FER202_GROUP_5/tree/assignment)

When you finish your assignment and pushed to your own branch, return to the
base branch to add the link to your part to the list. This file use Markdown
(which you should learn if you haven't already), so to add a new element to
the list, simply put:

```
- [<Your assignment name>](<your assignment link>)
```

Assignment List:

-
