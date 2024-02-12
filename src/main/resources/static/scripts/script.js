// Функция перехода по "главной" кнопке к форме оформления заказа
document.querySelector('#main-action-button').addEventListener('click', () => {
    document.querySelector('#products').scrollIntoView({ behavior: 'smooth' });
});

// Функция перехода к блокам страницы по навигационным ссылкам
const links = document.querySelectorAll('.menu-item > a');

links.forEach(link => link.addEventListener('click', () => {
    document.querySelector('#' + link.getAttribute('data-link'))
    .scrollIntoView({ behavior: 'smooth' })}));

// Функция добавления товара к форме оформления заказа и плавного перехода к форме
const buttons = document.querySelectorAll('.product-button'),
    burger = document.querySelector('#burgers'),
    name = document.querySelector('#userName'),
    phone = document.querySelector('#userPhone');

const addToOrder = function () {
    const product = this.closest('.products-item');
    const burgerId = product.getAttribute('data-base-burger-id');
    const burgerName = product.querySelector(".products-item-title").innerText;

    const existingBurgers = burger.getAttribute('data-base-burgers-id');
    const updatedBurgers = existingBurgers ? `${existingBurgers},${burgerId}` : burgerId;

    burger.setAttribute('data-base-burgers-id', updatedBurgers);
    burger.value = burger.value ? `${burger.value}, ${burgerName}` : burgerName;

    burger.parentElement.style.background = '';
    burger.parentElement.querySelector('.error-message').innerText = '';

    document.querySelector('.order').scrollIntoView({ behavior: 'smooth' });
}

// Назначение обработчиков на кнопку "Заказать"
buttons.forEach(button => button.addEventListener('click', addToOrder));

// Функция отправки post запроса
const postData = async (url = '', data = {}) => {
    try {
        // Формируем запрос
        const response = await fetch(url, {
            // Метод, если не указывать, будет использоваться GET
            method: 'POST',
            // Заголовок запроса
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            // Данные
            body: JSON.stringify(data),
        });
        if (response.status === 200) {
            return true;
        } else {
            throw new Error('Ошибка при выполнении запроса');
        }
    } catch (error) {
          console.error(error);
          return false;
    }
};

// Функция валидации в реальном времени
const validateInputs = (e) => {
    let isValid = true;
    const phonePattern = /^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$/;

    if (e.target.id === 'userName') {
        if (e.target.value === '' || e.target.value.length < 2 || e.target.value.length > 32) {
            e.target.parentElement.style.background = 'red';
            e.target.parentElement.querySelector('.error-message').innerText = 'Имя должно содержать от 2 до 32 символов';
            isValid = false;
        } else {
            e.target.parentElement.style.background = '';
            e.target.parentElement.querySelector('.error-message').innerText = '';
        }
    }

    if (e.target.id === 'userPhone') {
        if (e.target.value === '' || !phonePattern.test(e.target.value)) {
            e.target.parentElement.style.background = 'red';
            e.target.parentElement.querySelector('.error-message').innerText = 'Некорректный номер телефона';
            isValid = false;
        } else {
            e.target.parentElement.style.background = '';
            e.target.parentElement.querySelector('.error-message').innerText = '';
        }
    }

    if (e.target.id === 'order-action') {
        if (burger.value === '' && burger.getAttribute('data-base-burgers-id') === '') {
            burger.parentElement.style.background = 'red';
            burger.parentElement.querySelector('.error-message').innerText = 'Вы не выбрали бургеры из меню!';
            isValid = false;
        } else if (name.value === '' || name.value.length < 2 || name.value.length > 32) {
            name.parentElement.style.background = 'red';
            name.parentElement.querySelector('.error-message').innerText = 'Имя должно содержать от 2 до 32 символов';
            isValid = false;
        } else if(phone.value === '' || !phonePattern.test(phone.value)) {
            phone.parentElement.style.background = 'red';
            phone.parentElement.querySelector('.error-message').innerText = 'Некорректный номер телефона';
            isValid = false;
        } else {
            burger.parentElement.style.background = '';
            burger.parentElement.querySelector('.error-message').innerText = '';
            name.parentElement.style.background = '';
            name.parentElement.querySelector('.error-message').innerText = '';
            phone.parentElement.style.background = '';
            phone.parentElement.querySelector('.error-message').innerText = '';
        }
    }

    return isValid;
};

// Назначаем обработчик валидации
[burger, name, phone].forEach((item) => {
    item.addEventListener('input', validateInputs);
});

// Функция валидации и вызова функции отправки post запроса
document.querySelector('#order-action').addEventListener('click', async (e) => {
    e.preventDefault();

    const isValid = validateInputs(e);

    if (isValid) {
        console.log("from sending post");
        console.log(isValid);
        const burgersId = burger.getAttribute('data-base-burgers-id').split(',');
        const userName = name.value;
        const userPhone = phone.value;

        // Вызываем функцию отправки post запроса
        postData('/order', { 'userName':userName, 'userPhone':userPhone, 'burgersId':burgersId })
            .then((result) => {
                if (result) {
                    // Стираем строку, содержащую id выбранных бургеров
                    burger.setAttribute('data-base-burgers-id', '');

                    // Стираем значения заполненной формы
                    [burger, name, phone].forEach((item) => {
                        item.value = '';
                    });

                    alert('Спасибо за заказ! Мы скоро свяжемся с вами!');
                } else
                    alert('Нужно исправить форму заказа!');
            }).catch((error) => console.error(error));
    } else
        alert('Нужно исправить форму заказа!');
});

// функция изменения валюты
const prices = document.querySelectorAll('.products-item-price');

document.querySelector('#change-currency').addEventListener('click', (e) => {
    const currentCurrency = e.target.innerText;
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

    prices.forEach(price => price.innerText =
        +(price.getAttribute('data-base-price') * coefficient).toFixed(1) +
        ' ' + newCurrency);
});
