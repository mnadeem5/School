public interface Explorator
{
  public void clear();
  public boolean isEmpty();
  public Square getNext();
  public void add(Square s);
}