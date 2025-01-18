document.getElementById("airplane").value = localStorage.getItem("airplaneName");


let myChart;
let index

function InitChart(){
    // Получение контекста рендеринга для графика
    var ctx = document.getElementById('myChart').getContext('2d');

    var data = {
        labels: [],
        datasets: [{
            label: 'Показатель',
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1,
            data: []
        }]
    };

    // Создание и настройка графика
    myChart = new Chart(ctx, {
        type: 'line',
        data: data,
        options: {
            responsive: true,
            maintainAspectRatio: false
        }
    });

    index = 0;
}

InitChart();


// Функция для генерации случайных данных и обновления графика
function updateChart() {
    // Генерация случайного числа (в данном примере)
    var randomValue = Math.random() * 100;

    myChart.data.labels.push(index++);

    // Добавление нового значения в данные графика
    myChart.data.datasets.forEach(function(dataset) {
        dataset.data.push(randomValue);
    });

    // Удаление первого элемента, если количество данных больше 50 (иначе график будет слишком длинным)
    if (myChart.data.labels.length > 50) {
        myChart.data.labels.shift();
        myChart.data.datasets.forEach(function(dataset) {
            dataset.data.shift();
        });
    }

    // Обновление графика
    myChart.update();

    // Повторение обновления графика каждую секунду
    setTimeout(updateChart, 2000);
}

window.addEventListener('DOMContentLoaded', function() {
    // Начало обновления графика

    let xhr = new XMLHttpRequest();
    xhr.open('GET', "/api/sensors?airplaneId=" + localStorage.getItem("airplaneId"));
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                let data = JSON.parse(xhr.responseText);
                let selectElement = document.getElementById('sensor');
                data.forEach(function(sensor) {
                    let option = document.createElement('option');
                    option.value = sensor.id;
                    option.textContent = sensor.name;
                    selectElement.appendChild(option);
                });
            } else {
                console.error('Ошибка при получении данных:', xhr.statusText);
            }
        }
    };
    xhr.send();
});


let options = document.getElementById("sensor");
options.addEventListener("change", () => {
    myChart.destroy();
    InitChart();
    updateChart();
});


window.addEventListener('resize', function() {
    myChart.resize();
});
