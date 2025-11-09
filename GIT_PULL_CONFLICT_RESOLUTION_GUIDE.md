# Hướng Dẫn Xử Lý Lỗi Git Pull - Merge Conflict

## 📋 Tóm Tắt Lỗi

### Lỗi Gặp Phải
```
error: Your local changes to the following files would be overwritten by merge:
    back-end/bookverse/src/main/java/com/swp391/bookverse/controller/BookController.java
Please commit your changes or stash them before you merge.
```

### Nguyên Nhân
1. **Bạn có thay đổi chưa commit** trong file `BookController.java` (và các file khác)
2. **Remote (origin/main) cũng có thay đổi** trong cùng file đó
3. Git không thể tự động merge vì **sợ mất dữ liệu** của bạn

---

## 🔍 Phân Tích Chi Tiết

### Tại Sao Lỗi Này Xảy Ra?

1. **Trạng thái Local của bạn:**
   - File `BookController.java` đã được chỉnh sửa
   - Thay đổi chưa được commit
   - Git tracking thấy file modified

2. **Trạng thái Remote (origin/main):**
   - Người khác (hoặc bạn từ máy khác) đã push code mới
   - File `BookController.java` cũng có thay đổi khác
   - File `SecurityConfig.java` cũng có thay đổi

3. **Conflict:**
   - Khi pull, Git phát hiện cả local và remote đều sửa cùng file
   - Git không biết giữ phiên bản nào → dừng lại và yêu cầu bạn xử lý

---

## ✅ Giải Pháp Chi Tiết

### Phương Án 1: Commit Local Changes Trước Khi Pull (Khuyến nghị)

#### Bước 1: Kiểm tra trạng thái hiện tại
```bash
cd D:\SWP391_SU25_G5
git status
```

**Output mẫu:**
```
On branch wip/save-bookcontroller
Changes not staged for commit:
  modified:   back-end/bookverse/src/main/java/com/swp391/bookverse/controller/BookController.java
  modified:   front-end/src/App.tsx
  ... (nhiều file khác)
```

#### Bước 2: Tạo branch mới để lưu công việc (an toàn nhất)
```bash
git checkout -b wip/my-local-changes
```

#### Bước 3: Stage và commit tất cả thay đổi
```bash
git add back-end/bookverse/src/main/java/com/swp391/bookverse/controller/BookController.java
git commit -m "WIP: save BookController local changes"
```

Hoặc commit tất cả thay đổi:
```bash
git add .
git commit -m "WIP: save all local changes before merge"
```

#### Bước 4: Pull từ origin/main
```bash
git pull origin main
```

#### Bước 5: Xử lý merge conflicts (nếu có)

**Nếu có conflict, Git sẽ báo:**
```
Auto-merging back-end/bookverse/src/main/java/com/swp391/bookverse/controller/BookController.java
CONFLICT (content): Merge conflict in back-end/bookverse/src/main/java/com/swp391/bookverse/controller/BookController.java
Automatic merge failed; fix conflicts and then commit the result.
```

**Mở file conflict và tìm các marker:**
```java
<<<<<<< HEAD
// Code của bạn (local)
@GetMapping("/active/search/{title}")
public APIResponse<List<BookResponse>> searchActiveBooksByTitle(@PathVariable("title") String title) {
    // ...
}
=======
// Code từ remote
@GetMapping("/active/search/{title}")
public APIResponse<List<BookResponse>> searchActiveBooksByTitle(@PathVariable("title") String title) {
    // ... (có thể khác một chút)
}
>>>>>>> 363263ce5ad389486e6b89e2a5bd2b2854a616a5
```

**Cách xử lý:**
1. Xóa các marker (`<<<<<<<`, `=======`, `>>>>>>>`)
2. Quyết định giữ code nào (hoặc gộp cả hai)
3. Lưu file

#### Bước 6: Đánh dấu conflict đã resolved và commit
```bash
git add back-end/bookverse/src/main/java/com/swp391/bookverse/controller/BookController.java
git add back-end/bookverse/src/main/java/com/swp391/bookverse/configuration/SecurityConfig.java
git commit -m "Resolve merge conflicts: BookController and SecurityConfig"
```

#### Bước 7: Push lên remote (nếu cần)
```bash
git push origin HEAD
# hoặc
git push origin wip/my-local-changes
```

---

### Phương Án 2: Stash Changes (Tạm cất thay đổi)

#### Bước 1: Stash tất cả thay đổi local
```bash
git stash push -m "WIP: temporary save before pull"
```

**Hoặc stash chỉ một file cụ thể:**
```bash
git stash push -m "WIP: BookController only" -- back-end/bookverse/src/main/java/com/swp391/bookverse/controller/BookController.java
```

#### Bước 2: Pull từ remote
```bash
git pull origin main
```

#### Bước 3: Lấy lại thay đổi từ stash
```bash
git stash list
# Xem danh sách stash

git stash pop
# Áp dụng stash gần nhất và xóa khỏi stash list
```

**Nếu có conflict khi pop stash:**
- Xử lý tương tự như Phương Án 1 bước 5
- Sau khi sửa conflict:
```bash
git add <file-đã-sửa>
git stash drop  # Xóa stash đã áp dụng
```

---

### Phương Án 3: Reset Local Changes (Cẩn thận - mất dữ liệu!)

**⚠️ CHỈ dùng khi bạn chắc chắn không cần thay đổi local**

#### Bỏ thay đổi một file cụ thể:
```bash
git restore back-end/bookverse/src/main/java/com/swp391/bookverse/controller/BookController.java
```

#### Bỏ tất cả thay đổi chưa commit:
```bash
git reset --hard HEAD
```

#### Sau đó pull:
```bash
git pull origin main
```

---

## 🛠️ Công Cụ Hỗ Trợ

### Kiểm Tra Git Status Chi Tiết
```bash
git status --porcelain=1 --branch
```

### Xem Diff của File
```bash
git diff -- back-end/bookverse/src/main/java/com/swp391/bookverse/controller/BookController.java
```

### Xem Lịch Sử Commit
```bash
git log --oneline --graph --all --decorate -10
```

### Xem File Nào Đang Conflict
```bash
git status --short
# File có marker UU = unmerged (conflict)
```

---

## 🔧 Xử Lý Conflict Cụ Thể Trong Case Của Bạn

### File 1: `BookController.java`

**Conflict:**
- Cả local và remote đều có method `searchActiveBooksByTitle`
- Có thể trùng lặp hoặc khác nhau một chút

**Cách xử lý đã thực hiện:**
```java
// GIỮ LẠI phiên bản duy nhất (không duplicate):
@GetMapping("/active/search/{title}")
public APIResponse<List<BookResponse>> searchActiveBooksByTitle(@PathVariable("title") String title) {
    APIResponse<List<BookResponse>> response;
    response = bookService.searchActiveBooksByTitle(title);
    return response;
}
```

### File 2: `SecurityConfig.java`

**Conflict:**
- Biến `PUBLIC_GET_ENDPOINTS` có 2 phiên bản khác nhau
- Một có `api/cart/myCart`, một không có

**Cách xử lý đã thực hiện:**
```java
// GIỮ LẠI phiên bản có đầy đủ endpoints:
String[] PUBLIC_GET_ENDPOINTS = {
    "api/users/myInfo",
    "api/users/is-active/**",
    "api/authors/**", 
    "api/books/**", 
    "api/publishers/**", 
    "api/sup-categories/**", 
    "api/sub-categories/**", 
    "api/cart/myCart"  // ← Giữ lại endpoint này
};

// Xóa dòng duplicate:
// .requestMatchers(HttpMethod.PUT, PUBLIC_PUT_ENDPOINTS).permitAll() // ← Chỉ giữ 1 dòng
```

---

## 📝 Checklist Xử Lý Lỗi

- [x] 1. Chạy `git status` để xem file nào bị conflict
- [x] 2. Tạo branch mới hoặc stash changes
- [x] 3. Commit local changes (nếu cần giữ)
- [x] 4. Pull từ origin/main
- [x] 5. Xử lý merge conflicts:
  - [x] Mở file có marker `<<<<<<<`, `=======`, `>>>>>>>`
  - [x] Quyết định giữ code nào
  - [x] Xóa tất cả marker
  - [x] Lưu file
- [x] 6. Stage các file đã resolve: `git add <file>`
- [x] 7. Commit merge: `git commit -m "Resolve conflicts"`
- [x] 8. Kiểm tra build: `mvn -DskipTests package`
- [x] 9. Push lên remote (nếu cần): `git push origin <branch>`

---

## 🚀 Kiểm Tra Build Sau Khi Merge

### Maven Build (Backend)
```bash
cd D:\SWP391_SU25_G5\back-end\bookverse
mvn clean compile -DskipTests
```

Hoặc build full:
```bash
mvn clean package -DskipTests
```

### Nếu Build Bị Lỗi

**Lỗi thường gặp:**

1. **Lỗi JWT Signer Key:**
```
java.lang.IllegalArgumentException: The key's size is too small
```
→ Kiểm tra `application.yaml` có `jwt.signerKey` đủ dài (≥ 32 bytes)

2. **Lỗi Compile:**
```
java.lang.ExceptionInInitializerError
com.sun.tools.javac.code.TypeTag :: UNKNOWN
```
→ Có thể do Java version mismatch
→ Kiểm tra `pom.xml`: `<maven.compiler.source>` và `<maven.compiler.target>`
→ Chắc chắn dùng JDK tương thích (khuyến nghị JDK 17 hoặc 21)

---

## 💡 Best Practices

### Tránh Conflict Trong Tương Lai

1. **Pull thường xuyên:**
```bash
git pull origin main
# Làm mỗi ngày trước khi bắt đầu code
```

2. **Commit nhỏ, thường xuyên:**
```bash
git add .
git commit -m "feat: implement search endpoint"
git push
```

3. **Tạo feature branch:**
```bash
git checkout -b feature/my-feature
# Code trong branch riêng
# Merge vào main sau khi review
```

4. **Sync với remote trước khi code:**
```bash
git fetch origin
git status
# Xem có cần pull không
```

5. **Dùng Git GUI tools:**
- GitKraken
- SourceTree
- VS Code Git Extension
→ Dễ nhìn thấy conflicts và resolve trực quan

---

## 🔗 Tài Nguyên Tham Khảo

- [Git Documentation - Merge Conflicts](https://git-scm.com/docs/git-merge#_how_conflicts_are_presented)
- [Atlassian Git Tutorial - Merge Conflicts](https://www.atlassian.com/git/tutorials/using-branches/merge-conflicts)
- [GitHub - Resolving Merge Conflicts](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/addressing-merge-conflicts/resolving-a-merge-conflict-using-the-command-line)

---

## 📞 Khi Cần Hỗ Trợ

Nếu gặp vấn đề:
1. Chụp màn hình lỗi đầy đủ
2. Chạy `git status` và gửi output
3. Chạy `git log --oneline -5` xem commit gần nhất
4. Kiểm tra file conflict có marker không

---

**Tạo: 2025-11-09**  
**Trường hợp cụ thể:** Conflict khi pull origin/main do thay đổi local trong `BookController.java` và `SecurityConfig.java`  
**Đã giải quyết:** ✅ Merge conflicts resolved và committed

