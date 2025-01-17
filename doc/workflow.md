# Workflow

Workflow is a step-by-step process to accomplish a goal.
It composes multiple abilities or other workflows.

This is example of unit test workflow.
```mermaid
graph TD
    Start --> UnitTestWorkflow

    subgraph UnitTestWorkflow
        Parallel1
        Loop1
        Loop2
        Loop3
        Aggregate1
    end

%% Parallel: Parallel stages for writing unit tests
subgraph Parallel1 [Parallel: Simultaneous test code writing]
A1[Identify feature or module] --> A2[Define test cases]

subgraph ParallelTestCodeImplementation
A2 --> |In parallel| B1[Implement test case 1]
A2 --> |In parallel| B2[Implement test case 2]
A2 --> |In parallel| B3[Implement test case 3]
%% Additional parallel test implementations can be added as needed
end
end

%% Loop: Repeated test execution and result verification
subgraph Loop1 [Loop: Testing and debugging cycle]
D1[Test start] --> D2[Run test]
D2 --> D3[Verify results]
D3 --> |Test passes| D4[End loop]
D3 --> |Test fails| D5[Debug and fix code]
D5 --> D2
end



%% Connections between workflow stages
B1 --> Loop1
B2 --> Loop2
B3 --> Loop3

    Loop1 --> Aggregate1
    Loop2 --> Aggregate1
    Loop3 --> Aggregate1
    Aggregate1 --> End
```

## Pipeline

Pipeline is a workflow that Jarvis uses to execute a Pipeline of abilities in order.

Pipeline contain multiple abilities or workflows.
```mermaid
graph TD
    input --> Pipeline --> output
    subgraph Pipeline
        Ability1 --> Workflow2 --> Ability3
    end
```

## Router

Router is a workflow that Jarvis uses to route a request to the appropriate ability.

```mermaid
graph TD
    input --> Routing --> output
    subgraph Routing
        A{What is proper way to do this?} --> Ability1
        A --> Ability2
        A --> |yes|Workflow3
    end
```


## Parallelization

Parallelization is a workflow that Jarvis uses to orchestrate multiple workers to accomplish a goal.
```mermaid
graph TD
    input --> Parallelization --> output
    subgraph Parallelization
        Worker1 --> Worker2
        Worker1 --> Worker3
        Worker2 --> Worker4
        Worker3 --> Worker4
    end
```
## Loop
```mermaid
graph TD
    input --> Loop --> output
    subgraph Loop
        start --> Workflow --> ConditionCheck
        ConditionCheck --> |yes| stop
        ConditionCheck --> |no|Workflow
    end
```
Loop is a workflow that Jarvis uses to loop a Pipeline of abilities until a condition is met.


