// Функция перехода по "главной" кнопке к форме оформления заказа
document.querySelector('#main-action-button').onclick = function () {
  document.querySelector('#products').scrollIntoView({ behavior: 'smooth' });
};

// Функция перехода к блокам страницы по навигационным ссылкам
let links = document.querySelectorAll('.menu-item > a');

for (let i = 0; i < links.length; i++) {
  links[i].addEventListener('click', function () {
    document
      .querySelector('#' + links[i].getAttribute('data-link'))
      .scrollIntoView({ behavior: 'smooth' });
  });
}

// Функция добавления товара к форме оформления заказа и плавного перехода к форме оформления заказа
let buttons = document.querySelectorAll('.product-button');

const addToOrder = function () {
    const product = this.closest('.products-item');
    const burgerId = product.getAttribute('data-base-burger-id');
    const burgerName = product.querySelector(".products-item-title").innerText;
    const burgerInput = document.getElementById("burgers");
    burgerInput.setAttribute('data-base-burgers-id', burgerId);
    burgerInput.value = burgerName;

    document.querySelector('.order').scrollIntoView({ behavior: 'smooth' });
}

buttons.forEach(button => button.addEventListener('click', addToOrder));

// Функция отправки post запроса
let burger = document.querySelector('#burgers'),
  name = document.querySelector('#userName'),
  phone = document.querySelector('#userPhone');

const postData = async (url = '', data = {}) => {
  // Формируем запрос
  const response = await fetch(url, {
    // Метод, если не указывать, будет использоваться GET
    method: 'POST',
   // Заголовок запроса
    headers: {
      'Content-Type': 'application/json'
    },
    // Данные
    body: JSON.stringify(data)
  });
  return response.json();
}

// Функция валидации и вызова отправки запроса
document.querySelector('#order-action').addEventListener('click', function () {
  let hasError = false;

  [burger, name, phone].forEach((item) => {
    if (!item.value) {
      item.parentElement.style.background = 'red';
      hasError = true;
    } else {
      item.parentElement.style.background = '';
    }
  });

  if (!hasError) {
    const burgersId = burger.getAttribute('data-base-burgers-id').split(',');
    const userName = name.value;
    const userPhone = phone.value;

    postData('/order', { 'userName':userName, 'userPhone':userPhone, 'burgersId':burgersId });

    [burger, name, phone].forEach((item) => {
      item.value = '';
    });
    alert('Спасибо за заказ! Мы скоро свяжемся с вами!');
  }
});

// функция изменения валюты
let prices = document.querySelectorAll('.products-item-price');

document
  .querySelector('#change-currency')
  .addEventListener('click', function (e) {
    let currentCurrency = e.target.innerText;

    let newCurrency = '$';
    let coefficient = 1;

    if (currentCurrency === '$') {
      newCurrency = '₽';
      coefficient = 80;
    } else if (currentCurrency === '₽') {
      newCurrency = 'BYN';
      coefficient = 3;
    } else if (currentCurrency === 'BYN') {
      newCurrency = '€';
      coefficient = 0.9;
    } else if (currentCurrency === '€') {
      newCurrency = '¥';
      coefficient = 6.9;
    }

    e.target.innerText = newCurrency;

    for (let i = 0; i < prices.length; i++) {
      prices[i].innerText =
        +(prices[i].getAttribute('data-base-price') * coefficient).toFixed(1) +
        ' ' +
        newCurrency;
    }
  });
