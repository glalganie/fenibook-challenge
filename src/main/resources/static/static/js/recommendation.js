document.addEventListener('DOMContentLoaded', () => {
    // Seleziona gli elementi solo se siamo in una pagina che li contiene
    const moodButtons = document.querySelectorAll('.mood-btn');
    const resultContainer = document.getElementById('recommendation-result');

    // Se non ci sono i bottoni, interrompi lo script per non causare errori su altre pagine
    if (!moodButtons.length || !resultContainer) {
        return;
    }

    moodButtons.forEach(button => {
        button.addEventListener('click', async () => {
            const mood = button.dataset.mood;
            resultContainer.style.display = 'block';
            resultContainer.innerHTML = `<div class="spinner-border text-primary" role="status"><span class="visually-hidden">Caricamento...</span></div>`;

            try {
                // Chiama l'API del backend per ottenere il suggerimento
                const response = await fetch(`/api/recommendation/${mood}`);
                
                if (response.ok) {
                    const book = await response.json();
                    renderRecommendation(book, mood);
                } else {
                    renderNotFound();
                }
            } catch (error) {
                console.error('Errore durante la richiesta di raccomandazione:', error);
                renderError();
            }
        });
    });

    function renderRecommendation(book, mood) {
        const messages = {
            escape: "Per la tua voglia di evasione, perché non inizi:",
            reflect: "Per un momento di riflessione, ti consigliamo:",
            laugh: "Per farti una risata, prova con questo:",
            thrill: "Per una dose di adrenalina, ecco il libro giusto:"
        };

        resultContainer.innerHTML = `
            <p class="lead">${messages[mood] || 'Ti consigliamo:'}</p>
            <div class="d-flex align-items-center justify-content-center flex-wrap gap-3">
                <a href="/books/${book.id}">
                    <img src="${book.coverImageUrl}" alt="${book.title}" style="height: 120px; border-radius: 4px;" class="book-cover">
                </a>
                <div class="text-start">
                    <h5 class="mb-0">${book.title}</h5>
                    <p class="text-secondary mb-2">${book.author}</p>
                    <a href="/books/${book.id}" class="btn btn-sm btn-fenibook">Vedi Dettagli</a>
                </div>
            </div>
        `;
    }

    function renderNotFound() {
         resultContainer.innerHTML = `
            <p class="text-secondary">Non abbiamo trovato un libro adatto nella tua lista "Da Leggere" per questo umore.</p>
            <a href="/books" class="btn btn-fenibook-accent">Esplora il catalogo per aggiungerne di nuovi!</a>
        `;
    }
    
    function renderError() {
        resultContainer.innerHTML = `<p class="text-danger">Oops! Si è verificato un errore durante la ricerca.</p>`;
    }
});