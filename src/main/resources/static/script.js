function addToCart(itemId) {
    fetch('/cart/add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ "itemId": itemId }) // кавычки обязательны
    })
        .then(response => response.text())
        .then(text => alert(text))
        .catch(error => console.error("Ошибка запроса:", error));
}
