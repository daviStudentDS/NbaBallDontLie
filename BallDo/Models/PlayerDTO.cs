namespace BallDo.Models
{
    public class PlayerDTO
    {
        public string Name { get; set; }
        // Outras propriedades do jogador que você deseja permitir na criação
        public int? TeamId { get; set; } // ID do time (opcional)
    }

}
