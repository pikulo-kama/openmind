<#include "./include/header.ftl">

    <form method="POST" action="/login">
        <div class="form-group">
            <input name="username" type="text" placeholder="Username" autofocus />
            <input name="password" type="password" placeholder="Password" />
            <button type="submit">Log in</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>

<#include "./include/footer.ftl">