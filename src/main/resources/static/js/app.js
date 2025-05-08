// public/js/app.js
const API_URL = 'http://localhost:8080/api/tasks';

async function loadTasks(filter = '') {
  const url = filter ? `${API_URL}?filter=${filter}` : API_URL;
  const res = await fetch(url);
  const tasks = await res.json();

  const tbody = document.getElementById('taskTableBody');
  tbody.innerHTML = '';

  tasks.forEach(task => {
    const row = document.createElement('tr');

    row.innerHTML = `
      <td>${task.title}</td>
      <td>${task.description}</td>
      <td>${task.dueDate}</td>
      <td>${task.completed ? '✅ Completed' : '❌ Pending'}</td>
      <td>
        ${!task.completed ? `<button class="btn btn-sm btn-success me-2" onclick="completeTask(${task.id})">Mark Complete</button>` : ''}
        <button class="btn btn-sm btn-danger" onclick="deleteTask(${task.id})">Delete</button>
      </td>
    `;

    tbody.appendChild(row);
  });
}

document.getElementById('taskForm').addEventListener('submit', async (e) => {
  e.preventDefault();

  const title = document.getElementById('title').value;
  const description = document.getElementById('description').value;
  const dueDate = document.getElementById('dueDate').value;

  await fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title, description, dueDate, completed: false })
  });

  e.target.reset();
  loadTasks(document.getElementById('filterSelect').value);
});

async function deleteTask(id) {
  await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
  loadTasks(document.getElementById('filterSelect').value);
}

async function completeTask(id) {
  await fetch(`${API_URL}/${id}/complete`, { method: 'PUT' });
  loadTasks(document.getElementById('filterSelect').value);
}

document.getElementById('filterSelect').addEventListener('change', (e) => {
  loadTasks(e.target.value);
});

// Load tasks on page load
loadTasks();
