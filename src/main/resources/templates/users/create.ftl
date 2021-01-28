<#include "../include/header.ftl">

<form action="/u/create" method="POST">
    <div>
        <span>Username: </span>
        <label for="username">
            <input type="text" name="username" required autofocus>
        </label>
    </div>

    <div>
        <span>E-mail: </span>
        <label for="email">
            <input type="email" name="email" required>
        </label>
    </div>

    <div>
        <span>Password: </span>
        <label for="password">
            <input type="password" name="password" required>
        </label>
    </div>

    <div>
        <span>Repeat password: </span>
        <label for="repeatPassword">
            <input type="password" name="repeatPassword" required>
        </label>
    </div>

    <div>
        <span>Birth date: </span>
        <label for="birthDate">
            <input type="date"  name="birthDate" required>
        </label>
    </div>

    <div>
        <select name="deletePeriod" title="Delete me after: " required>
            <#list periods as period>
                <#if period == "NEVER">
                    <option value="${period}" selected>${period.getVerboseName()}</option>
                <#else>
                    <option value="${period}">${period.getVerboseName()}</option>
                </#if>
            </#list>
        </select>
    </div>

    <div>
        <select name="role" title="Role" required>
            <#list roles as role>
                <#if role == "ROLE_USER">
                    <option value="${role}" selected>${role.getName()}</option>
                <#else>
                    <option value="${role}" >${role.getName()}</option>
                </#if>
            </#list>
        </select>
    </div>

    <button type="submit">Submit</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<#include "../include/footer.ftl">