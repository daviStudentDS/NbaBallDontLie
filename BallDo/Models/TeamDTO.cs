namespace BallDo.Models
{
    public class TeamDTO
    {
        public string Name { get; set; }
        // Outras propriedades do time que você deseja permitir na criação
        public int? CoachId { get; set; } // ID do treinador (opcional)
    }

}
