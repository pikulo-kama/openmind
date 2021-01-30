<form action="/u/update" method="POST">
    <div>
        <label for="username">
            <input type="text" name="username" required autofocus>
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
        <button type="submit">Save</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </div>
</form>