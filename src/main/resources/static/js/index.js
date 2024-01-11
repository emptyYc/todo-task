document.addEventListener('DOMContentLoaded', function () {
    // 调用真实的接口
    fetchData();
});

async function fetchData() {
    showLoading();

    try {
        const response = await fetch('/api/todoTask/taskInfo/paginQuery', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "page": 1,
                "size": 10
            })
        });
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || '服务调用异常');
        }

        const data = await response.json();
        hideLoading();
        renderTaskList(data.data.list);
    } catch (error) {
        hideLoading();
        showErrorMessage(error.message || '服务调用异常');
    }
}

function renderTaskList(tasks) {
    const taskList = document.getElementById('taskList');
    taskList.innerHTML = '';

    tasks.forEach(task => {
        const taskElement = document.createElement('div');
        taskElement.className = 'task';

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.className = 'checkbox';
        checkbox.checked = task.isCompleted === '1';
        // 禁用复选框
        checkbox.disabled = true;

        const label = document.createElement('label');
        label.innerText = task.content;

        taskElement.appendChild(checkbox);
        taskElement.appendChild(label);
        taskList.appendChild(taskElement);
    });
}

function showLoading() {
    document.getElementById('loading').style.display = 'block';
}

function hideLoading() {
    document.getElementById('loading').style.display = 'none';
}

function showErrorMessage(message) {
    const errorElement = document.getElementById('error');
    errorElement.style.display = 'block';
    errorElement.innerText = message;
}