# Git and GitHub Step by Step

## 1. Upload this folder to your repository (Activity 1)

Create a new empty repo on github.com (for example `java-oop-practicals`), then in this folder:

```bash
git init
git add .
git commit -m "Add Java OOP practicals, theory answers and Student Management System"
git branch -M main
git remote add origin https://github.com/<your-username>/java-oop-practicals.git
git push -u origin main
```

No Git installed? On github.com open the repo, click **Add file > Upload files**, drag the folders in and press **Commit changes**.

## 2. Commit regularly

Teachers monitor commit history. Make small commits with clear messages:

```bash
git add Questions/Programming/Q05_RaceCondition.java
git commit -m "Add Q5: race condition demo with synchronized fix"
git push
```

## 3. Collaborate using branches and pull requests (Activity 3)

Do this with a friend. Both of you work on the same repo (add them under *Settings > Collaborators*) or use a fork.

```bash
git checkout -b feature/add-teacher-menu
# edit code
git add .
git commit -m "Add menu to list teachers"
git push -u origin feature/add-teacher-menu
```

Then on GitHub: **Compare & pull request > write a description > Create pull request**. Your partner reviews and merges.

Keep your branch up to date:

```bash
git checkout main
git pull
git checkout feature/add-teacher-menu
git merge main
```

## 4. Review a peer's code (Activity 5)

1. Open their pull request on GitHub, go to the **Files changed** tab.
2. Click the **+** next to a line to leave a comment.
3. Click **Review changes** and choose *Comment*, *Approve* or *Request changes*.

Review checklist:

- [ ] Does the code compile and run?
- [ ] Are names meaningful (classes `Student`, methods `addMarks`)?
- [ ] Are fields private with getters/setters?
- [ ] Are exceptions handled properly (no empty `catch`)?
- [ ] Is there duplicate code that could use inheritance or a method?
- [ ] Are there comments or README for the main logic?

Example comment: *"`marks` is public here. Make it `private` and add a getter to keep encapsulation."*

## 5. Mentoring a slow learner (supervised pair programming)

1. Ask your mentee to fork or clone the teacher's repo.
2. Sit together, you explain, they type.
3. They commit and push the fix themselves.
4. Review their pull request with kind, specific comments.
