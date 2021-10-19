package seedu.address.model;

import java.util.LinkedList;
import java.util.Queue;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;


public class ModelManagerBuilder {

    private Queue<Command> commandQueue;

    /**
     * Constructor for the {@code ModelManagerBuilder class}
     */

    public ModelManagerBuilder() {
        this.commandQueue = new LinkedList<>();
    }

    /**
     * Constructor for the {@code ModelManagerBuilder class}
     *
     * @param queue existing queue to construct the {@code ModelManagerBuilder} from
     */

    public ModelManagerBuilder(Queue<Command> queue) {
        this.commandQueue = queue;
    }

    /**
     * Builds the {@code ModelManager} with a command
     *
     * @param command Command to build {@code ModelManager} with
     * @return a new ModelManager with that command added to the queue
     */

    public ModelManagerBuilder with(Command command) {
        commandQueue.add(command);
        return new ModelManagerBuilder(commandQueue);
    }

    /**
     * Executes the commands stored in the queue, in order.
     *
     * @param modelManager the {@code ModelManager} to start execution from
     */

    public void executeCommands(ModelManager modelManager) {
        Command c;
        while (!commandQueue.isEmpty()) {
            c = commandQueue.poll();
            try {
                c.execute(modelManager);
            } catch (CommandException ce) {
                // for testing commands which are wrong
                System.out.println("Model manager test: " + ce);
            }
        }
    }

    /**
     * Builds the {@code ModelManager} from its {@code commandQueue}
     *
     * @return the constructed {@code ModelManager}
     */

    public ModelManager build() {
        ModelManager modelManager = new ModelManager();
        executeCommands(modelManager);
        return modelManager;
    }

    /**
     * Builds the {@code ModelManager} from its {@code commandQueue}
     *
     * @param modelManager the {@code modelManager} to start executing commands from
     * @return the constructed {@code ModelManager}
     */

    public ModelManager build(ModelManager modelManager) {
        executeCommands(modelManager);
        return modelManager;
    }
}
