<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Редактировать книгу</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .form-container { padding: 20px; border: 1px solid #ccc; border-radius: 5px; max-width: 600px; margin: auto; }
        .form-container input, .form-container textarea { width: 98%; padding: 8px; margin-bottom: 10px; }
        .form-container button { padding: 10px 15px; background-color: #007bff; color: white; border: none; cursor: pointer; }
        .form-container a { display: inline-block; margin-top: 10px; }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Редактировать книгу</h2>
    <form action="updateBook" method="post">
        <!-- Скрытое поле для передачи ID книги -->
        <input type="hidden" name="id" value="<c:out value='${book.id}' />"/>

        <label for="title">Название:</label>
        <input type="text" id="title" name="title" value="<c:out value='${book.title}' />" required><br>

        <label for="author">Автор:</label>
        <input type="text" id="author" name="author" value="<c:out value='${book.authorFullname}' />" required><br>

        <label for="year">Год публикации:</label>
        <input type="number" id="year" name="year" value="<c:out value='${book.publicationYear}' />" required><br>

        <label for="genre">Жанр:</label>
        <input type="text" id="genre" name="genre" value="<c:out value='${book.genre}' />"><br>

        <label for="pageCount">Количество страниц:</label>
        <input type="number" id="pageCount" name="pageCount" value="<c:out value='${book.pageCount}' />"><br>

        <label for="shortDescription">Краткое описание:</label>
        <textarea id="shortDescription" name="shortDescription" rows="4"><c:out value='${book.shortDescription}' /></textarea><br>

        <button type="submit">Сохранить изменения</button>
    </form>
    <a href="books">Вернуться к списку</a>
</div>

</body>
</html>
