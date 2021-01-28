<form action="/u/update-password" method="POST">

    <div>
        Old password:
        <label for="oldPassword">
            <input type="password" name="oldPassword" required autofocus>
        </label>
    </div>

    <div>
        New password:
        <label for="password">
            <input type="password" name="password" required>
        </label>
    </div>

    <div>
        Repeated password:
        <label for="passwordRepeat">
            <input type="password" name="passwordRepeat" required>
        </label>
    </div>

    <div>
        <button type="submit">Update</button>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>