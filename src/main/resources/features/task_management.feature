Feature: Task Management
  # Given 1
    Background:
      Given the following tasks exist:
        | title       | status |
        | Buy Milk    | false  |
        | Fix Bug     | true   |

      Scenario: User clears only the active tasks
        When I request to clear "active" tasks
        Then there should be 1 task remaining
        And the remaining task should be "Fix Bug"

      Scenario: User clears only the inactive tasks
        When I request to clear "inactive" tasks
        Then there should be 1 task remaining
        And the remaining task should be "Buy Milk"

      Scenario: Tagging task as done
        When I mark the 0th task as "done"
        Then there should be 2 tasks with status "true"

      Scenario: Tagging task as not done
        When I mark the 0th task as "not done"
        Then there should be 1 tasks with status "true"

  # Given 2
    Scenario: User adds and deletes tasks with an empty db
      Given the database has 0 tasks
      When I add 4 new tasks
      And I delete 2 tasks
      Then there should be 2 task remaining

  # Given 3
    Scenario: User adds and deletes tasks with a populated db
      Given the database has 100 tasks
      When I add 0 new tasks
      And I delete 50 tasks
      Then there should be 50 task remaining