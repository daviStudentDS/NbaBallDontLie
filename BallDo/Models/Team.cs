using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace BallDo.Models
{
    public class Team
    {
        [Key]
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        [Required]
        public int FoundedYear { get; set; }


        public IEnumerable<Player>? Players { get; set; }

        [Required]
        public int CoachId { get; set; }
        public Coach? Coach { get; set; }
    }

















}
