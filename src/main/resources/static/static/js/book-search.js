document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('book-search-input');
    const resultsContainer = document.getElementById('book-results-container');

    if (!searchInput || !resultsContainer) {
        return;
    }

    let debounceTimer;

    searchInput.addEventListener('keyup', () => {
        clearTimeout(debounceTimer);

        // Il debounce previene troppe chiamate API mentre l'utente digita
        debounceTimer = setTimeout(async () => {
            const query = searchInput.value.trim();

            if (query.length < 3) {
                // Se la query è troppo corta, potresti voler mostrare di nuovo tutti i libri
                // o semplicemente non fare nulla. Per ora, non facciamo nulla.
                return;
            }

            // Mostra un indicatore di caricamento
            resultsContainer.innerHTML = '<div class="col-12 text-center py-5"><div class="spinner-border text-primary" role="status"></div></div>';

            try {
                // Chiama l'API di ricerca del backend (questo endpoint va creato)
                const response = await fetch(`/api/books/search?q=${encodeURIComponent(query)}`);
                if (response.ok) {
                    const books = await response.json();
                    renderBookResults(books);
                } else {
                    renderSearchError('Nessun libro trovato.');
                }
            } catch (error) {
                console.error('Errore durante la ricerca dei libri:', error);
                renderSearchError('Errore di connessione. Riprova più tardi.');
            }
        }, 300); // Attendi 300ms dopo che l'utente ha smesso di scrivere
    });

    function renderBookResults(books) {
        if (books.length === 0) {
            renderSearchError('Nessun libro trovato che corrisponda alla tua ricerca.');
            return;
        }

        // 'books' è un array di oggetti Book
        const bookCardsHtml = books.map(book => `
            <div class="col-6 col-md-4 col-lg-3 mb-4">
                <div class="card card-fenibook h-100">
                    <a href="/books/${book.id}">
                        <img src="${book.coverImageUrl || '/img/default-cover.png'}" class="card-img-top book-cover" alt="${book.title}">
                    </a>
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title book-title">
                            <a href="/books/${book.id}" class="text-decoration-none text-dark">${book.title}</a>
                        </h5>
                        <p class="card-text book-author">${book.author}</p>
                        <div class="mt-auto">
                            <form action="/books/add-to-library/${book.id}" method="post">
                                <button type="submit" class="btn btn-fenibook btn-sm w-100">Aggiungi a "Da Leggere"</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        `).join('');

        resultsContainer.innerHTML = bookCardsHtml;
    }

    function renderSearchError(message) {
        resultsContainer.innerHTML = `<div class="col-12 text-center py-5"><p class="text-secondary">${message}</p></div>`;
    }
});