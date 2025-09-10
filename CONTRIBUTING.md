# Guidelines

## Workflow

1. Clone the repository

- For HTTPS:

```bash
git clone https://github.com/nananguyen15/FA25_FER202_GROUP_5.git && cd FA25_FER202_GROUP_5
```

- For SSH:

```bash
git clone git@github.com:nananguyen15/FA25_FER202_GROUP_5.git && cd FA25_FER202_GROUP_5
```

2. Create a branch for your work

New features MUST be developed from `main` branch

```bash
git fetch origin && git checkout -b feature/my-feature origin/main
```

Naming convention:

- New features: `feature/<name>`
- Bug fixes: `bug/<name>`

3. Keep your branch updated

```bash
git fetch origin && git rebase origin/main
```

You MUST keep your branch up to date with `main` branch and resolve any
conflict between your branch and `main` branch before pushing.

4. Commit your changes

- Use concise, easy to understand commit messages
- Follow commit format: `<type>[optional scope]: <short description>`
  - `feat`: for code changes that add, change, or remove features
  - `fix`: for code changes to fix bugs
  - `style`: for code changes that only alter code styles e.g. indentation, semicolon,..
  - `docs`: for changes that only affect documentation
  - `refactor`: for code changes that do not affect app beahaviour
  - `chore`: miscellaneous changes e.g. update dependencies, lockfile, ...
- If your changes introduce breaking changes, it must be marked by a `!` symbol
  e.g. `feat(homepage)!: change url of homepage to /home`

5. Pushing your branch and create a Pull Request (PR):

You must first push your changes to your own branch in the remote repository

```bash
git push -u origin [your branch name]
```

Then you can open a Pull Request to merge your branch into the main branch.
The title of the PR should follow the same format as the commit message.
