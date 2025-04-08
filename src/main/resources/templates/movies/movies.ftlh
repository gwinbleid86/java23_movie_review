<#import "../layout.ftlh" as main>
<@main.layout>

    <a href="/movies/create" class="btn btn-success">Create new movie</a>
    <a href="/auth/login" class="btn btn-success">Login</a>
    <div class="row">
        <div class="col-12">
            <table class="table table-image">
                <thead>
                <tr>
                    <th scope="col">Title</th>
                    <th scope="col">Poster</th>
                    <th scope="col">Release year</th>
                    <th scope="col">Director</th>
                </tr>
                </thead>
                <tbody>
                <#list movies as movie>
                    <tr>
                        <td>${movie.name}</td>
                        <td class="w-25">
                            <img src="api/images/movies/1" class="img-fluid img-thumbnail" alt="Sheep">
                        </td>
                        <td>${movie.year}</td>
                        <td>${movie.director!"-"}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@main.layout>