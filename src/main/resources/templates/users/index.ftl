

<div>
    Username: <p>${user.username}#${user.id}</p>
    Role: <p>${user.role.getName()}</p>
    Last activity at ${user.lastLoginDate}
    <br>

    <div>
        <#list user.posts as post>
            <h3>${post.topic}</h3>
            <h3>Likes: ${post.likes}</h3>
            <h3>Dislikes: ${post.dislikes}</h3>
            <br>
        </#list>
    </div>

    <#if sessionUser?? && sessionUser.role == 'ROLE_ADMIN'>

        <#if user.isEnabled() >
            <form action="/u/deactivate/${user.id}" method="POST">
                <button type="submit">Deactivate</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>

        <#else >

            <form action="/u/activate/${user.id}" method="POST">
                <button type="submit">Activate</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>

            <form action="/u/delete/${user.id}" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button type="submit">Delete</button>
            </form>

        </#if>

    </#if>
</div>