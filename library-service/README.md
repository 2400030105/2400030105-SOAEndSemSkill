# Library Service - JWT Security

## Import into STS
File -> Import -> Maven -> Existing Maven Projects -> select this folder.
Then right-click project -> Maven -> Update Project.
Run: right-click LibraryApplication.java -> Run As -> Spring Boot App (http://localhost:8080)

## Users
| Username  | Password | Role      |
|-----------|----------|-----------|
| admin     | admin123 | ADMIN     |
| librarian | lib123   | LIBRARIAN |
| john      | user123  | USER      |

## Test
1. POST /auth/login  {"username":"admin","password":"admin123"}  -> copy token
2. POST /books  with header  Authorization: Bearer <token>  body {"title":"Clean Code","author":"Robert Martin"}
3. Repeat with john's token -> 403 Forbidden
4. No token -> 401 Unauthorized
