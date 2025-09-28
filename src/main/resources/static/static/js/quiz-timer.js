document.addEventListener('DOMContentLoaded', () => {
    const timerElement = document.getElementById('quiz-timer');
    if (timerElement) {
        let timeLeft = 60; // 60 secondi per rispondere

        const timerInterval = setInterval(() => {
            timeLeft--;
            timerElement.textContent = `Tempo rimasto: ${timeLeft}s`;

            if (timeLeft <= 0) {
                clearInterval(timerInterval);
                // Logica per gestire il tempo scaduto
                // es. invia automaticamente il form o mostra un messaggio
                alert('Tempo scaduto!');
            }
        }, 1000);
    }
});