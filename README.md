# Switchly API Assignment

## 1. Add `description` to `Flag`

Added an optional `description` field to the `Flag` model.

### Files changed
- `[model/Flag.java](https://github.com/AARYANG0SWAMI/Switchly/blob/Assignment/api/src/main/java/live/switchly/api/model/Flag.java)`
  - Added `description`
  - Added it to the constructor
  - Added `getDescription()`

- `[dto/CreateFlagRequest.java](https://github.com/AARYANG0SWAMI/Switchly/blob/Assignment/api/src/main/java/live/switchly/api/dto/CreateFlagRequest.java)`
  - Added optional `description`

- `[service/FlagService.java](https://github.com/AARYANG0SWAMI/Switchly/blob/Assignment/api/src/main/java/live/switchly/api/service/FlagService.java)`
  - Updated flag creation to accept and store `description`

- `[controller/FlagController.java](https://github.com/AARYANG0SWAMI/Switchly/blob/Assignment/api/src/main/java/live/switchly/api/controller/FlagController.java)`
  - Updated the create endpoint to pass `request.description()`


The repository did not need any changes because it stores the complete `Flag` object.

`description` is optional, so a request without it is valid:

{
  "key": "new-checkout",
  "name": "New Checkout"
}

It can also be provided:

{
  "key": "new-checkout",
  "name": "New Checkout",
  "description": "New checkout implementation"
}

---

## 2. Delete a Flag

Added:

DELETE /api/v1/flags/{flagId}


Therefore the method mapping is:

@DeleteMapping("/flags/{flagId}")

### Behavior

- Existing flag → `204 No Content`
- Non-existent flag → `404 Not Found`

The service first checks whether the flag exists using the existing `getById()` method. If it does not exist, `NotFoundException` is thrown and the existing exception handling returns `404`.

If the flag exists, it is removed from the repository and the controller returns `204 No Content`.

### Files changed
- `[controller/FlagController.java](https://github.com/AARYANG0SWAMI/Switchly/blob/Assignment/api/src/main/java/live/switchly/api/controller/FlagController.java)`
- `[service/FlagService.java](https://github.com/AARYANG0SWAMI/Switchly/blob/Assignment/api/src/main/java/live/switchly/api/service/FlagService.java)`
- `[repository/FlagRepository.java](https://github.com/AARYANG0SWAMI/Switchly/tree/Assignment/api/src/main/java/live/switchly/api/repository)`
- `[repository/InMemoryFlagRepository.java](https://github.com/AARYANG0SWAMI/Switchly/blob/Assignment/api/src/main/java/live/switchly/api/repository/InMemoryFlagRepository.java)`

### Verification

The endpoint was tested with curl.

Deleting an existing flag returned:

HTTP/1.1 204

Deleting the same flag again returned:

HTTP/1.1 404

with the application's existing NOT_FOUND error response.

---

## 3. Environment-Specific Flag State

The current design has:

private boolean enabled;

This means the enabled state is global for a flag.

Therefore the current design cannot represent:

new-checkout → ON in test
new-checkout → OFF in production

because there is only one `enabled` value.

### Required design change

The enabled state should become environment-specific.

For example:

Flag
- id
- organizationId
- projectId
- key
- name
- description

FlagEnvironment
- flagId
- environment
- enabled

This would allow:

new-checkout
- test → enabled = true
- production → enabled = false

Flag evaluation would then need both the flag and the environment, for example:

isEnabled("new-checkout", "test") → true
isEnabled("new-checkout", "production") → false

The API would also need to include the environment when changing or evaluating a flag.

I would not create separate flags such as `new-checkout-test` and `new-checkout-production`, because they represent the same logical feature. Environment should be modeled as configuration/state of the same flag.

---

## Summary

Implemented:
- Optional `description` field on `Flag`
- Creation support for `description`
- `DELETE /api/v1/flags/{flagId}`
- `204 No Content` for successful deletion
- `404 Not Found` for non-existent flags

Design consideration:
- The current global `enabled` field must become environment-specific to support different flag states between test and production.
