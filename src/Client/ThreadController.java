package Client;

import Client.Interfaces.IThreadController;

public class ThreadController implements IThreadController
{
    private boolean cancellationRequested = false;

    public boolean isCancellationRequested() {
        return cancellationRequested;
    }

    public void Cancel() {
        cancellationRequested = true;
    }

    public void reset() {
        cancellationRequested = false;
    }
}
