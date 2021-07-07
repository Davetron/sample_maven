package co.databeast.pipeline;

import static co.databeast.conveyor.Conveyor.conveyor;
import static co.databeast.conveyor.job.DefaultJob.job;
import static co.databeast.conveyor.stage.DefaultStage.stage;
import static co.databeast.conveyor.task.DummyTask.dummyTask;
import static co.databeast.conveyor.task.GitCloneTask.gitClone;
import static co.databeast.conveyor.task.MavenTask.maven;

public class Pipeline {

    public static final String REPOSITORY_URI = "https://github.com/Davetron/sample_maven.git";

    public static void main(String[] args) {
        conveyor("co.databeast.pipeline.Pipeline",
                stage("Build",
                        job("Application Build",
                                gitClone(REPOSITORY_URI),
                                maven("install")
                        )
                )/*,
                stage("Test",
                        job("Azure Acceptance Test",
                                dummyTask("deploy Azure instance"),
                                //maven("test"), // TODO how should params be passed/shared between tasks?
                                dummyTask("destroy Azure instance")
                        ),
                        job("Openstack Acceptance Test",  // TODO how best to optionally trigger jobs or stages?
                                dummyTask("deploy Openstack instance"),
                                //maven("test"),
                                dummyTask("destroy Openstack instance")
                        )
                )*/
        ).start();
    }
}
