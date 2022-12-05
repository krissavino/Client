package Client.Interfaces;

public interface IThreadController
{
    boolean isCancellationRequested();

    void Cancel();

    void reset();
}
