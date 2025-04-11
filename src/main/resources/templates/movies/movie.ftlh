<#import "../layout.ftlh" as main>
<@main.layout >

    <div class="jumbotron">
        <div class="row">
            <img class="col-4" src="/api/images/movies/${movie.id}" alt="${movie.name}">
            <h1 class="display-4 col-6">${movie.name}</h1>
        </div>
        <p class="lead">${movie.director.fullName}</p>
        <p class="lead">${movie.year}</p>
        <hr class="my-4">
        <p>${movie.description}</p>
    </div>

</@main.layout>