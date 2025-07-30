const canvas = document.getElementById("gameCanvas");
const ctx = canvas.getContext("2d");
const box = 20;

let snake = [{ x: 9 * box, y: 10 * box }];
let direction = null;
let score = 0;
let game;
let gameOver = false;
let gameStarted = false;

let food = randomFood();

// Handle keyboard input
document.addEventListener("keydown", (event) => {
    if (!gameStarted || gameOver) {
        resetGame();
        game = setInterval(draw, 100);
        gameStarted = true;
    }

    if (event.key === "ArrowLeft" && direction !== "RIGHT") direction = "LEFT";
    else if (event.key === "ArrowUp" && direction !== "DOWN") direction = "UP";
    else if (event.key === "ArrowRight" && direction !== "LEFT") direction = "RIGHT";
    else if (event.key === "ArrowDown" && direction !== "UP") direction = "DOWN";
});

function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    // === Draw food with drop shadow ===
    ctx.save();
    ctx.shadowColor = 'rgba(255, 0, 0, 0.5)';
    ctx.shadowBlur = 8;
    ctx.shadowOffsetX = 2;
    ctx.shadowOffsetY = 2;
    ctx.fillStyle = "red";
    ctx.beginPath();
    ctx.arc(food.x + box / 2, food.y + box / 2, box / 2.5, 0, Math.PI * 2);
    ctx.fill();
    ctx.restore();

    // === Draw snake with drop shadow ===
    for (let i = 0; i < snake.length; i++) {
        ctx.save();
        ctx.shadowColor = 'rgba(0, 100, 0, 0.5)';
        ctx.shadowBlur = 6;
        ctx.shadowOffsetX = 2;
        ctx.shadowOffsetY = 2;
        ctx.fillStyle = i === 0 ? "#32CD32" : "#006400";
        ctx.beginPath();
        ctx.arc(snake[i].x + box / 2, snake[i].y + box / 2, box / 2.2, 0, 2 * Math.PI);
        ctx.fill();
        ctx.restore();
    }

    // Move snake
    let head = { x: snake[0].x, y: snake[0].y };
    if (direction === "LEFT") head.x -= box;
    else if (direction === "RIGHT") head.x += box;
    else if (direction === "UP") head.y -= box;
    else if (direction === "DOWN") head.y += box;
    else return;

    // Check game over
    if (
        head.x < 0 || head.x >= canvas.width ||
        head.y < 0 || head.y >= canvas.height ||
        collision(head, snake)
    ) {
        clearInterval(game);
        gameOver = true;
        setTimeout(() => {
            alert("Game Over! Final Score: " + score + "\nPress any key to restart.");
        }, 100);
        return;
    }

    // Check food collision
    if (head.x === food.x && head.y === food.y) {
        score++;
        document.getElementById("score").innerText = score;
        food = randomFood();
    } else {
        snake.pop();
    }

    snake.unshift(head);
}

function collision(head, array) {
    return array.slice(1).some(segment => segment.x === head.x && segment.y === head.y);
}

function randomFood() {
    return {
        x: Math.floor(Math.random() * (canvas.width / box)) * box,
        y: Math.floor(Math.random() * (canvas.height / box)) * box
    };
}

function resetGame() {
    snake = [{ x: 9 * box, y: 10 * box }];
    direction = null;
    score = 0;
    gameOver = false;
    gameStarted = false;
    food = randomFood();
    document.getElementById("score").innerText = score;
    clearInterval(game);
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}
