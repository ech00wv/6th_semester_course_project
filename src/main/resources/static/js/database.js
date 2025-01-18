document.getElementById("airplane").value = localStorage.getItem("airplaneName");
function isValidString(inputString) {
    // Регулярное выражение для проверки, что строка содержит только кириллицу, латиницу, цифры или пробелы
    var regex = /^[a-zA-Zа-яА-Я0-9\s]{3,100}$/;
    // Проверяем, соответствует ли строка регулярному выражению
    return regex.test(inputString);
}

document.addEventListener("DOMContentLoaded", () => {
    if (localStorage.getItem("airplaneName") === null){
        document.querySelectorAll(".repairs, .sensors, .airplane_delete, .airplane_name").forEach(element => {
            element.style.display = "none";
        });
    }
})

document.getElementById("deleteAirplane").addEventListener("click", () => {
    document.getElementById("airplaneName").style.border = "1px solid #ccc";
    let xhr = new XMLHttpRequest();
    let airplaneName = document.getElementById("airplaneName");
    if (!isValidString(airplaneName.value)){
        airplaneName.style.border = "2px solid red";
        return;
    }
    xhr.open('DELETE', "/api/airplanes?airplaneName=" + airplaneName.value);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                alert("Самолет успешно удален!")
                localStorage.removeItem("airplaneName");
                localStorage.removeItem("airplaneId");
                window.location.href="/main";
            } else {
                alert("Самолета с таким названием не существует!");
            }
        }
    };
    xhr.send();
})

document.getElementById("deleteSensor").addEventListener("click", () => {
    document.getElementById("sensorName").style.border = "1px solid #ccc";
    let xhr = new XMLHttpRequest();
    let sensorName = document.getElementById("sensorName");
    if (!isValidString(sensorName.value)){
        sensorName.style.border = "2px solid red";
        return;
    }
    xhr.open('DELETE', "/api/sensors?sensorName=" + sensorName.value + "&airplaneId=" + localStorage.getItem("airplaneId"));
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                alert("Датчик успешно удален!")
            } else {
                alert("Датчика с таким названием не существует!");
            }
        }
    };
    xhr.send();
})

function validateDate(dateInput) {
    let date = new Date(dateInput);
    // Проверяем, что день от 1 до 31, месяц от 1 до 12, и год больше или равен 2000
    return date.getDate() >= 1 && date.getDate() <= 31 &&
        date.getMonth() + 1 >= 1 && date.getMonth() + 1 <= 12 &&
        date.getFullYear() >= 2000;
}


document.getElementById("deleteRepair").addEventListener("click", () => {
    document.getElementById("repairDate").style.border = "1px solid #ccc";
    let xhr = new XMLHttpRequest();
    let repairDate = document.getElementById("repairDate");
    if (!validateDate(repairDate.value)){
        repairDate.style.border = "2px solid red";
        return;
    }
    xhr.open('DELETE', "/api/repairs?repairDate=" + repairDate.value + "&airplaneId=" + localStorage.getItem("airplaneId"));
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                alert("Ремонт успешно удален!")
            } else {
                alert("Ремонта по этой дате не существует!");
            }
        }
    };
    xhr.send();
})