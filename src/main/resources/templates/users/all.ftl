

<#list users as user>
    <div>
        Username: <p>${user.getUsername()}#${user.getId()}</p>
        Role: <p>${user.getRole().getName()}</p>
        Last activity at ${user.getLastLoginDate()}
        <br>

        <div>
            <#list user.getPosts() as post>
                <h3>${post.getTopic()}</h3>
                <h3>Likes: ${post.getLikes()}</h3>
                <h3>Dislikes: ${post.getDislikes()}</h3>
                <br>
            </#list>
        </div>

        <a href="/u/${user.getId()}">Open account</a>
    </div>
</#list>