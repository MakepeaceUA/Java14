<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Библиотека</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; text-align: left; padding: 8px; }
        th { background-color: #f2f2f2; }
        .container { padding: 20px; border: 1px solid #ccc; border-radius: 5px; margin-bottom: 20px; }
        .container input { width: 98%; padding: 8px; margin-bottom: 10px; }
        .container button { padding: 10px 15px; color: white; border: none; cursor: pointer; }
        .add-button { background-color: #4CAF50; }
        .search-button { background-color: #008CBA; }
        .delete-button { background-color: #f44336; }
        .edit-button { background-color: #ffc107; text-decoration: none; color: white; padding: 5px 10px; display: inline-block; }
    </style>
</head>
<body>

<div class="container">
    <h2>Поиск книг</h2>
    <form action="books" method="get">
        <input type="text" name="title" placeholder="Название книги">
        <input type="text" name="author" placeholder="ФИО автора">
        <input type="number" name="year" placeholder="Год выпуска">
        <input type="text" name="genre" placeholder="Жанр книги">
        <input type="number" name="pageCount" placeholder="Количество страниц">
        <input type="text" name="descriptionKeyword" placeholder="Слово в описании">
        <button type="submit" class="search-button">Найти</button>
    </form>
</div>

<div class="container">
    <h2>Добавить новую книгу</h2>
    <form action="addBook" method="post">
        <input type="text" name="title" placeholder="Название книги" required>
        <input type="text" name="author" placeholder="ФИО автора" required>
        <input type="number" name="year" placeholder="Год публикации" required>
        <input type="text" name="genre" placeholder="Жанр">
        <input type="number" name="pageCount" placeholder="Количество страниц">
        <input type="text" name="shortDescription" placeholder="Краткое описание">
        <button type="submit" class="add-button">Добавить книгу</button>
    </form>
</div>

<h2>Каталог книг</h2>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Автор</th>
            <th>Год</th>
            <th>Жанр</th>
            <th>Страниц</th>
            <th>Действия</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td><c:out value="${book.id}"/></td>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.authorFullname}"/></td>
                <td><c:out value="${book.publicationYear}"/></td>
                <td><c:out value="${book.genre}"/></td>
                <td><c:out value="${book.pageCount}"/></td>
                <td>
                    <a href="editBook?id=${book.id}" class="edit-button">Редактировать</a>

                    <!-- Форма для удаления книги -->
                    <form action="deleteBook" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${book.id}">
                        <button type="submit" class="delete-button">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>