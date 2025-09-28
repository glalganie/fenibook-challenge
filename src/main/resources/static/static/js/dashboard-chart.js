document.addEventListener('DOMContentLoaded', () => {
    const chartCanvas = document.getElementById('progressChart');
    if (chartCanvas) {
        // Funzione asincrona per caricare i dati dall'API
        const fetchChartData = async () => {
            try {
                const response = await fetch('/dashboard/api/progress-chart');
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                renderChart(data);
            } catch (error) {
                console.error('Failed to fetch chart data:', error);
                chartCanvas.parentElement.innerHTML = '<p class="text-secondary text-center">Impossibile caricare i dati del grafico.</p>';
            }
        };

        const renderChart = (data) => {
            const ctx = chartCanvas.getContext('2d');
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: data.labels, // Es: ['Gen', 'Feb', 'Mar']
                    datasets: [{
                        label: 'Libri Letti',
                        data: data.values, // Es: [5, 8, 3]
                        borderColor: 'var(--color-primary)',
                        backgroundColor: 'rgba(217, 74, 56, 0.1)',
                        fill: true,
                        tension: 0.4
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        y: { beginAtZero: true }
                    },
                    plugins: {
                        legend: { display: false }
                    }
                }
            });
        };

        fetchChartData();
    }
});