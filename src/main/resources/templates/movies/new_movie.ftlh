<#import "../layout.ftlh" as main>
<@main.layout>
    <form action="/movies/create" method="post">
        <input type="text" name="name" class="form-control" placeholder="Title">
        <input type="number" name="releaseYear" class="form-control" placeholder="Release year">
        <div class="mb-3">
            <label for="desc" class="form-label">Description</label>
            <textarea name="description" id="desc" rows="3" class="form-control"></textarea>
        </div>
        <input type="text" class="form-control" name="director" placeholder="Director">
        <input type="text" class="form-control" name="castMemberRole" placeholder="Cast Member Role">
        <input type="text" class="form-control" name="castMemberName" placeholder="Cast Member Name">

        <button type="submit" class="btn btn-success">Save</button>


    </form>
</@main.layout>